<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.Utilisateur"%>
<%@ page import="java.util.List"%>
<%@ page import="model.LigneAbsence"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" >
<title>Validation des justificatifs</title>
<script type="text/javascript">
	$(document).ready(function($) 
	{



		//--->deletel single row > start
		function remove_curr_tbl_row(ele) 
		{	 
			ele.closest('tr').css('background-color', 'red');
			
			ele.closest('tr').fadeOut('slow', function()
			{
				$(this).remove();
			}); 	
		};

		$(document).on('click', '.btn_delete', function(event) 
		{
			event.preventDefault();

			remove_curr_tbl_row($(this));
		}); 		
		//--->deletel single row > start
 		

		//--->select/unselect all > start
 		function select_unselect_checkbox (this_el, select_el) 
 		{

			if(this_el.prop("checked"))
			{
				select_el.prop('checked', true);
			}
			else
			{ 
				select_el.prop('checked', false);				 
			}
 		};

		$(document).on('change', '.select_all_items', function(event) 
		{
			event.preventDefault();

			var ele = $(document).find('.item_id'); 

			select_unselect_checkbox($(this), ele); 
		});
		//--->select/unselect all > end



		//--->deletel selected rows > start
		function remove_all_checked_val(ele) 
		{	 
			ele.each(function(index, v1)
			{   
				if($(this).prop("checked")) 
		 		{
					$(this).closest('tr').css('background-color', 'red');
					
					$(this).closest('tr').fadeOut('slow', function()
					{
						$(this).remove();
					}); 
				} 
			});
		};
		$(document).on('click', '.btn_delete_val', function(event) 
		{
			event.preventDefault();

			var ele = $(document).find('.item_id'); 
			var v1 = remove_all_checked_val(ele);
		});
		//--->deletel selected rows > end



		//--->get selected rows values > start

		function get_all_checked_val(ele, attr_lookup) 
		{  
			var get_obj = [];
			ele.each(function(index, v1)
			{   
				if($(this).prop("checked")) 
		 		{
					get_obj.push($(this).attr(attr_lookup));
				} 
			});			
			return get_obj;
		};


		$(document).on('click', '.btn_get_val', function(event) 
		{
			event.preventDefault();

			var ele = $(document).find('.item_id'); 

			var v1 = get_all_checked_val(ele, 'option_id');

			var v2 = ''
			+'<pre class="bg-secondary">' 
			+JSON.stringify(v1, null, 5)
			+'</pre>';

			$(document).find('.post_msg').html(v2);

		});		
		//--->get selected rows values > end
		

 
	});
	</script>

<body>


<div class="container">
<form action="">
<table class="table">
  <thead>
  <tr>
    <th scope="col"></th>
      <th scope="col">Absences</th>
      <th scope="col"></th>
      <th scope="col"></th>
      <th scope="col"></th>
      <th scope="col"></th>
      <th scope="col"></th>
      <th scope="col"></th>
  </tr>
  </thead>
  <thead>
    <tr>
     <th scope="col">Prenom</th>
      <th scope="col">Nom</th>
      <th scope="col">Cours</th>
      <th scope="col">Debut</th>
      <th scope="col">Fin</th>
      <th scope="col">Groupe</th>
      <th scope="col">Justificatifs</th>
      <th scope="col"></th>
      
      
    </tr>
  </thead>
  
  <tbody>
   <% 
        for (LigneAbsence labs : (List<LigneAbsence>)request.getAttribute("listeAbs") ) {
        	out.println("<tr><td>"+labs.getPrenom()+"</td>");
        	out.println("<td>"+labs.getNom()+"</td>");
        	out.println("<td>" + labs.getNomCours() +"</td>");
        	out.println("<td>" + labs.getDtdebut() +"</td>");
        	out.println("<td>" + labs.getDtfin() +"</td>");
        	out.println("<td>" + labs.getNomGroupe()+"</td>");
        	out.println("<td></td>");
        	out.println("<td><input type=\"checkbox\" value= "+ labs.getAbsid() +" name=\"cb\"></input></td></tr>");
        } 
      %>
  </tbody>
</table>
<button type="submit" class="btn btn-success"Style="margin-left: 100%;margin-top: 40px">Valider</button>
</form>
</div>
</body>
</html>