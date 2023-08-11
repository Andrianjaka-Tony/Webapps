let logos = Array.from(document.querySelectorAll(".logo_svg"));
logos.forEach((logo) => {
  logo.innerHTML = `
    <svg class="logo" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 297.067 210.398">
      <g id="logo" transform="translate(-406.915 -226.471)">
        <path
          id="Path_1"
          data-name="Path 1"
          d="M388.328,272.686,598.2,228.428,492.548,272.686V402.6"
          transform="translate(19)"
          fill="none"
          stroke-width="4"
        />
        <g
          id="Ellipse_1"
          data-name="Ellipse 1"
          transform="translate(523 288)"
          fill="#fff"
          stroke-width="4"
        >
          <ellipse cx="55.5" cy="57.5" rx="55.5" ry="57.5" stroke="none" />
          <ellipse cx="55.5" cy="57.5" rx="53.5" ry="55.5" fill="none" />
        </g>
        <path
          id="Path_2"
          data-name="Path 2"
          d="M653.875,436.869V249.843l57.107,57.107V409.743H666.724"
          transform="translate(-9)"
          fill="none"
          stroke-width="4"
        />
        <g
          id="Ellipse_2"
          data-name="Ellipse 2"
          transform="translate(558 326)"
          fill="#fff"
          stroke-width="3"
        >
          <circle cx="19.5" cy="19.5" r="19.5" stroke="none" />
          <circle cx="19.5" cy="19.5" r="18" fill="none" />
        </g>
      </g>
    </svg>
  `;
});
