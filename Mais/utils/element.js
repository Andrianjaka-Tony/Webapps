/**
 *
 * @param {string} element
 * @param {string} className
 * @returns {HTMLElement}
 */
export function createElement(element, className) {
  let reponse = document.createElement(element);
  reponse.className = className;
  return reponse;
}

/**
 *
 * @return {void}
 */
export function resetNavbar() {
  document.querySelectorAll(".navbar__items__item").forEach((element) => {
    element.classList.remove("active");
  });
}
