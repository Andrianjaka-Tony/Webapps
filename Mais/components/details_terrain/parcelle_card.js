import { rendreAnnomalie } from "../../pages/liste_annomalie.js";
import { createElement } from "../../utils/element.js";

/**
 *
 * @returns {HTMLDivElement}
 */
export default function (parcelle) {
  let reponse = createElement("div", "parcelle__card");

  reponse.innerHTML = `
      <h1 class="parcelle__card__title">${parcelle.titre}</h1>
      <span class="parcelle__card__info">Surface <span>${parcelle.surface}ha</span></span>
      <span class="parcelle__card__info">Hauteur des tiges <span>${parcelle.tige.hauteur}cm</span></span>
      <span class="parcelle__card__info">Couleur <span>${parcelle.tige.couleur}</span></span>
      <span class="parcelle__card__info">Nombre de tiges <span>${parcelle.tige.nombre}</span></span>
      <span class="parcelle__card__info">Moyenne des bourgeons <span>${parcelle.tige.bourgeons}</span></span>
      <span class="parcelle__card__info">Paysan <span>${parcelle.paysan.nom}</span></span>
  `;

  reponse.addEventListener("click", () => {
    rendreAnnomalie(parcelle.id);
  });

  return reponse;
}
