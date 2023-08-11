import { resetNavbar } from "../utils/element.js";
import { rendreTerrain } from "./liste_terrain.js";

document.getElementById("terrain").addEventListener("click", function () {
  rendreTerrain();
  resetNavbar();
  this.classList.add("active");
});

rendreTerrain();
