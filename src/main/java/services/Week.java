package services;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import model.SessionCours;

public class Week {
	private List<List<RenduSessionCours>> semaineCours;
	public Week(List<List<SessionCours>> semaineCours) {
		List<List<RenduSessionCours>> rendu = semaineCours.stream()
				.map(journee -> {
					List<RenduSessionCours> renduJournee = journee.stream()
							.map(cours -> new RenduSessionCours(cours))
							.map(cours -> calculerHauteurCours(cours))
							.toList();
					return organize(renduJournee);
				})
				.toList();
		
		this.semaineCours = rendu;
	}
	
	public List<RenduSessionCours> getJournee(int index) {
		return this.semaineCours.get(index);
	}
	
	private List<RenduSessionCours> organize(List<RenduSessionCours> journee) {
		List<List<RenduSessionCours>> colonnes = new ArrayList<>();
		OffsetDateTime derniereFinEvenement = null;
		
		for (RenduSessionCours cours: journee) {
			if (derniereFinEvenement != null && cours.sessionCours.getDebut().isAfter(derniereFinEvenement)) {
				regrouperCours(colonnes);
				colonnes.clear();
				derniereFinEvenement = null;
			}
			
			boolean insere = false;
			for (List<RenduSessionCours> colonne: colonnes) {
				if (!cours.intersecte(colonne.get(colonne.size() - 1))) {
					colonne.add(cours);
					insere = true;
					break;
				}
			}
			
			if (!insere) {
				List<RenduSessionCours> colonne = new ArrayList<>();
				colonne.add(cours);
				colonnes.add(colonne);
			}
			
			if (derniereFinEvenement == null || cours.sessionCours.getFin().isAfter(derniereFinEvenement)) {
				derniereFinEvenement = cours.sessionCours.getFin();
			}
		}
		
		if (colonnes.size() > 0) {
			regrouperCours(colonnes);
		}
		
		return journee;
	}
	
	private void regrouperCours(List<List<RenduSessionCours>>colonnes) {
		System.out.println("regrouper " + colonnes.size());
		for (int i = 0; i < colonnes.size(); ++i) {
			List<RenduSessionCours> colonneCourante = colonnes.get(i);
			
			for (RenduSessionCours cours: colonneCourante) {
				float etendueColonne = etirerEvenement(cours, i, colonnes);
				
				float fI = (float) i;
				float fcs = (float) colonnes.size();
				cours.x = ((fI * 100f) / fcs);
				cours.w = (100f * etendueColonne / fcs);
			}
		}
	}
	
	private float etirerEvenement(RenduSessionCours coursAEtendre, int index, List<List<RenduSessionCours>> colonnes) {
		float etendueColonne = 1f;
		List<List<RenduSessionCours>> colonnesRestantes = colonnes.subList(index + 1, colonnes.size());
		for (int i = 0; i < colonnesRestantes.size(); ++i) {
			List<RenduSessionCours> colonneCourante = colonnesRestantes.get(index);
			for (RenduSessionCours cours: colonneCourante) {
				if (coursAEtendre.intersecte(cours)) {
					return etendueColonne;
				}
			}
			
			++etendueColonne;
		}
		
		return etendueColonne;
	}
	
	private RenduSessionCours calculerHauteurCours(RenduSessionCours cours) {
		OffsetDateTime debut = cours.sessionCours.getDebut();
		OffsetDateTime fin = cours.sessionCours.getFin();
		
		float debutJournee = 8f * 60;
		float debutMinutes = debut.getHour() * 60f + debut.getMinute() - debutJournee;
		float finMinutes = fin.getHour() * 60f + fin.getMinute() - debutJournee;
		float dureeMinutes = finMinutes - debutMinutes;
		
		cours.y = 100f * (debutMinutes / 60f);
		cours.h = 100f * (dureeMinutes / 60f);
		
		return cours;
	}
	
	public static class RenduSessionCours {
		public static final float LARGEUR_MAX = 100f;
		private final SessionCours sessionCours;
		private float x = 0f;
		private float y = 0f;
		private float w = 100f;
		private float h = 0f;
		
		public RenduSessionCours(SessionCours sessionCours) {
			System.out.println(sessionCours);
			this.sessionCours = sessionCours;
		}
		
		public SessionCours getSessionCours() {
			return this.sessionCours;
		}
		
		public boolean intersecte(RenduSessionCours other) {
			return between(sessionCours.getDebut(), other.sessionCours.getDebut(), other.sessionCours.getFin())
					|| between(sessionCours.getFin(), other.sessionCours.getDebut(), other.sessionCours.getFin());
		}
		
		private static boolean between(OffsetDateTime date, OffsetDateTime min, OffsetDateTime max) {
			return date.isAfter(min) && date.isBefore(max);
		}

		public float getX() {
			return x;
		}

		public float getY() {
			return y;
		}

		public float getW() {
			return w;
		}

		public float getH() {
			return h;
		}
		
		
	}
}
