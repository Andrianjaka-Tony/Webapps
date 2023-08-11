import { Terrain } from "../../models/terrain.js";
import { createElement } from "../../utils/element.js";
import { default as rendreCarte } from "./terrain_card.js";

/**
 *
 * @param {Terrain[]} terrains
 */
export default function (terrains) {
  let reponse = createElement("div", "terrain__container");
  terrains.forEach((terrain) => {
    reponse.appendChild(rendreCarte(terrain));
  });

  return reponse;
}
