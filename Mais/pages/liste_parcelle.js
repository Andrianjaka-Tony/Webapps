import { ajax } from "../utils/ajax.js";
import { API, root } from "../utils/const.js";
import { default as parcelle } from "../components/details_terrain/parcelle_container.js";
import { default as renderEngrais } from "./meilleur_composiion.js";

function listenLink(terrain) {
  document.querySelector(".link").addEventListener("click", async () => {
    let response = await ajax(
      `${API}Composition?terrain=${terrain}`,
      "GET",
      null
    );
    response = JSON.parse(response);
    renderEngrais(response);
  });
}

function classEmenetInner(coco) {
  let response = "";
  coco.forEach((element) => {
    response += `<p>${element.nom} ${element.production / element.quantite} ${
      element.production
    } ${element.quantite}</p>`;
  });
  return response;
}

export async function rendreParcelle(terrain) {
  let data = await ajax(`${API}Terrain/data?terrain=${terrain}`, "GET", null);
  data = JSON.parse(data);

  let coco = await ajax(`${API}Terrain/result?terrain=${terrain}`, "GET", null);
  coco = JSON.parse(coco);

  root.classList.add("invisible");
  window.setTimeout(() => {
    root.innerHTML =
      "<p class='link'>Meilleure composition</p>" + classEmenetInner(coco);
    root.appendChild(parcelle(data));
    root.classList.remove("invisible");
    listenLink(terrain);
  }, 300);
}
