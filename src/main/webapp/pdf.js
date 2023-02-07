document.addEventListener('DOMContentLoaded', function () {

    const specialElementHandlers = {
        '#editor': function (element, renderer) {
            return true;
        }
    };
	
	document.querySelector('#btn-one').addEventListener('click', event => {
		const pdf = new jsPDF('p', 'pt', 'a4');
		pdf.addHTML(document.body, function() {
			pdf.setFont("helvetica");
			pdf.setFontType("bold");
			pdf.setFontSize(900);
		  	pdf.save('web.pdf');
		});
	});
});