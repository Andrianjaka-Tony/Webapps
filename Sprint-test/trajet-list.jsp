<%@page import="java.util.Set, java.util.Vector, models.Trajet, java.util.HashMap"%>
<%
  HashMap<String, Object> data = (HashMap<String, Object>) request.getAttribute("data");
  String trajet = "trajet";
  Vector<Trajet> trajets = (Vector<Trajet>) data.get(trajet);
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <style>
      *, *::before, *::after {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: Open sans;
      }

      .container {
        width: 100vw;
        padding: 20px;
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
      }

      .input {
        outline: none;
        padding: 0 10px;
        height: 38px;
        margin-top: 10px;
      }

      p {
        text-transform: uppercase;
      }

      .btn {
        padding-inline: 30px;
        height: 40px;
        border: none;
        background: #121212;
        color: white;
        cursor: pointer;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <% for (Trajet t : trajets) { %>
          <form action="/Sprint-test/trajet/validate" method="get">
            <p><%= (t.getDepart() + " - " + t.getArrivee()) %></p>
            <input type="hidden" name="trajet" value="<%= t.getId() %>">
            <input class="input" type="date" name="date_arrivee">
            <input class="input" type="time" name="heure_arrivee">
            <input type="submit" class="btn" value="Valider">
          </form>
      <% } %>
    </div>
  </body>
</html>
