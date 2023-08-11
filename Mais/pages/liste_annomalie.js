import { ajax } from "../utils/ajax.js";
import { API, root } from "../utils/const.js";
import { default as annomalie } from "../components/details_parcelle/annomalie_card.js";
import { renderPrevisionForm } from "../components/prevision/prevision_form.js";

function listenLink(parcelle) {
  document.querySelector(".link").addEventListener("click", () => {
    renderPrevisionForm(parcelle);
  });
}

export async function rendreAnnomalie(parcelle) {
  let data = await ajax(
    `${API}Parcelle/data?parcelle=${parcelle}`,
    "GET",
    null
  );
  data = JSON.parse(data);
  root.classList.add("invisible");
  window.setTimeout(() => {
    root.innerHTML = "<p class='link'>Resultats des meilleurs engrais</p>";
    data.forEach((a) => {
      root.appendChild(annomalie(a));
    });
    root.classList.remove("invisible");
    listenLink(parcelle);
  }, 300);
}
