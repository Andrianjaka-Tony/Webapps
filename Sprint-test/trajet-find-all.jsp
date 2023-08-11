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

      .trajet-card {
        padding: 30px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.116);
      }
    </style>
  </head>
  <body>
    <div class="container">
      <% for (Trajet t : trajets) { %>
          <div class="trajet-card">
            <p><%= t.getDepart() + " - " + t.getArrivee() %></p>
            <p><%= t.getDate_depart() + " : " + t.getHeure_depart() %></p>
            <p><%= t.getEtat() %></p>
          </div>
      <% } %>
    </div>
  </body>
</html>