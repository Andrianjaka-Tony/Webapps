import { ajax } from "../utils/ajax.js";
import { API, root } from "../utils/const.js";
import { default as terrain } from "../components/terrain/terrain_container.js";

export async function rendreTerrain() {
  let data = await ajax(`${API}index/data`, "GET", null);
  data = JSON.parse(data);
  root.classList.add("invisible");
  window.setTimeout(() => {
    root.innerHTML = "";
    root.appendChild(terrain(data));
    root.classList.remove("invisible");
  }, 300);
}
