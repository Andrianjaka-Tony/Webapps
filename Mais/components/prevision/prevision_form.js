import { ajax } from "../../utils/ajax.js";
import { API, root } from "../../utils/const.js";
import { default as render } from "../../pages/meilleur_engrais_prevision.js";

function listenForm() {
  let form = document.querySelector(".form");
  form.addEventListener("submit", async (event) => {
    event.preventDefault();
    let parcelle = document.getElementById("parcelle").value;
    let response = await ajax(
      `${API}Prevision?parcelle=${parcelle}`,
      "GET",
      null
    );
    render(response);
  });
}

/**
 *
 * @param {string} parcelle
 * @return {void}
 */
export function renderPrevisionForm(parcelle) {
  let inner = `
    <form class="form">
      <h1 class="title">Voir le meilleur engrais</h1>
      <input type="hidden" id="parcelle" name="parcelle" value="${parcelle}" />
      <input type="submit" class="btn" value="Voir le resultat" />
    </form>
  `;
  root.classList.add("invisible");
  window.setTimeout(() => {
    root.innerHTML = inner;
    root.classList.remove("invisible");
    listenForm();
  }, 300);
}
