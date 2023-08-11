/**
 *
 * @param {string} url
 * @param {string} method
 * @param {Object} data
 * @returns {Promise<string>}
 */
export function ajax(url = "", method = "", data = null) {
  let xhr = new XMLHttpRequest();
  xhr.open(method, url);
  xhr.send(data);

  return new Promise((resolve) => {
    xhr.addEventListener("load", () => {
      resolve(xhr.responseText);
    });
  });
}
