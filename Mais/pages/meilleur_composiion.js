import { root } from "../utils/const.js";
import { default as renderEngrais } from "../components/composition/meilleure_composition.js";

export default function (engrais) {
  root.classList.add("invisible");
  window.setTimeout(() => {
    root.innerHTML = "";
    engrais.forEach((element) => {
      root.appendChild(renderEngrais(element));
    });
    root.classList.remove("invisible");
  }, 300);
}
