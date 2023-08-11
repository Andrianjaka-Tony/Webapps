import { createElement } from "../../utils/element.js";

/**
 *
 * @param {*} annomalie
 * @returns {HTMLDivElement}
 */
export default function (annomalie) {
  let reponse = createElement("div", "annomalie__card");

  reponse.innerHTML = `
      <h1 class="annomalie__card__title">${annomalie.date}</h1>
  `;
  annomalie.descriptions.forEach((desc) => {
    let p = createElement("p", "annomalie__card__description");
    p.innerHTML = desc;
    reponse.appendChild(p);
  });

  return reponse;
}
