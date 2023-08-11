import { Terrain } from "../../models/terrain.js";
import { rendreParcelle } from "../../pages/liste_parcelle.js";
import { createElement } from "../../utils/element.js";

/**
 *
 * @param {Terrain} terrain
 * @returns {HTMLDivElement}
 */
export default function (terrain) {
  let reponse = createElement("div", "terrain__card");

  reponse.innerHTML = `
      <h1 class="terrain__card__title">${terrain.titre}</h1>
      <span class="terrain__card__info">Surface <span>${terrain.surface}ha</span></span>
      <span class="terrain__card__info">Parcelles <span>${terrain.nombreParcelle}</span></span>
      <span class="terrain__card__info">Derniere analyse <span>${terrain.etude}</span></span>
  `;

  reponse.addEventListener("click", async () => {
    rendreParcelle(terrain.id);
  });

  return reponse;
}
