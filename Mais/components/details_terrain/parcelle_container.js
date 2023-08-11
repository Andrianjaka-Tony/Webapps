import { createElement } from "../../utils/element.js";
import { default as rendreCarte } from "./parcelle_card.js";

/**
 *
 */
export default function (parcelles) {
  let reponse = createElement("div", "parcelle__container");
  parcelles.forEach((parcelle) => {
    reponse.appendChild(rendreCarte(parcelle));
  });

  return reponse;
}
