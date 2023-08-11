<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Index page</title>

    <style>
      *,
      *::before,
      *::after {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
      }

      body {
        overflow-x: hidden;
      }

      .container {
        height: 100vh;
        width: 100vw;
        display: flex;
        align-items: center;
        flex-wrap: wrap;
      }

      .link-container {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        gap: 20px;
      }

      .link {
        text-decoration: none;
        padding: 20px 40px;
        color: black;
        border: black 1px solid;
        font-family: Open sans;
        position: relative;
      }

      .link::before {
        content: "";
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        transition: 300ms;
        background: #1212121e;
        transform: scaleX(0);
        transform-origin: left center;
        transition-timing-function: ease-in-out;
      }

      .link:hover::before {
        transform: scaleX(1);
      }
    </style>
  </head>
  <body>
    <div class="container">
      <div class="link-container">
        <a href="/Sprint-test/trajet/form" class="link">Creer un trajet</a>
        <a href="/Sprint-test/trajet/findAll" class="link">Liste de tous les trajets</a>
        <a href="/Sprint-test/trajet/list" class="link">Valider l'arrivee d'un trajet</a>
        <a href="/Sprint-test/profil" class="link">S'authentifier</a>
      </div>
    </div>
  </body>
</html>
