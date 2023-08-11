import { createElement } from "../../utils/element.js";
export default function (engrais) {
  let reponse = createElement("div", "composition__card");

  reponse.innerHTML = `
      <p class="text">${engrais.nom} <span>${engrais.taux * 100}%</span></p>
  `;

  return reponse;
}
