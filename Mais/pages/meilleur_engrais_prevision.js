import { previsionContainer } from "../components/prevision/prevision_container.js";
import { root } from "../utils/const.js";

export default function (response) {
  root.classList.add("invisible");
  window.setTimeout(() => {
    root.innerHTML = "";
    root.appendChild(previsionContainer(response));
    root.classList.remove("invisible");
  }, 300);
}
