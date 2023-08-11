import { createElement } from "../../utils/element.js";

/**
 *
 * @param {string} response
 */
export function previsionContainer(response) {
  let reponse = createElement("div", "prevision__container");

  console.log(response);
  // let prix = response.split(",,,")[0];
  let best1 = JSON.parse(response.split(",,,")[0]);
  let best2 = JSON.parse(response.split(",,,")[1]);
  let result = response.split(",,,")[2];
  reponse.innerHTML = `
    <p style="margin: 20px 0">1</p>
    <p class="text">Meilleur engrais: <span>${best1.nom}</span></p>
    <p style="margin: 20px 0">3</p>
    <p class="text">Meilleur engrais: <span>${best2.nom}</span></p>
    <p style="margin: 20px 0" class="text">Recolte avec additif: <span>${result}</span></p>
  `;

  return reponse;
}
