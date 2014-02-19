function Yazdirma() {
	var x = document.getElementById(p1).value;
	document.getElementById(p2).value = x;

}

function yemekleriYazdirma() {
	var e = document.getElementById("yiyeceklerkutusu");
	secilenYemek = e.options[e.selectedIndex].value.replace(/[0-9]/g, '');
	var secilenYemekFiyati = parseInt(e.options[e.selectedIndex].value.replace(
			/[^0-9]/g, ''));
	var adet = parseInt(document.getElementById("yemekadetkutusu").value);
	if (adet == "" || isNaN(adet)) {
		adet = 1;
	}
	document.getElementById("secilenyemeklertexti").value += secilenYemek;
}