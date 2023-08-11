<%@page import="java.util.Set, java.util.Vector, models.Voiture, models.Chauffeur, java.util.HashMap"%>
<%
  HashMap<String, Object> data = (HashMap<String, Object>) request.getAttribute("data");
  String voiture = "voiture";
  String chauffeur = "chauffeur";
  Vector<Voiture> voitures = (Vector<Voiture>) data.get(voiture);
  Vector<Chauffeur> chauffeurs = (Vector<Chauffeur>) data.get(chauffeur);
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

      .form-container {
        height: 100vh;
        width: 100vw;
        display: flex;
        align-items: center;
        justify-content: center;
      }

      .form {
        height: 100vh;
        display: flex;
        flex-direction: column;
        align-items: flex-start;
      }

      .title {
        font-weight: 300;
        margin-top: 50px;
        margin-bottom: 20px;
      }

      .input-container {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        gap: 5px;
        margin-bottom: 15px;
      }

      label {
        font-size: 12px;
      }

      .input {
        width: 300px;
        height: 40px;
        outline: none;
        border: 1px rgba(0, 0, 0, 0.233) groove;
        padding: 0 10px;
      }

      .select-container {
        width: 300px;
        height: 40px;
        padding: 0 10px;
        display: flex;
        align-items: center;
        justify-content: center;
        border: 1px rgba(0, 0, 0, 0.233) groove;
      }

      select {
        outline: none;
        border: none;
        width: 100%;
        height: 100%;
      }

      .btn {
        padding: 10px 25px;
        outline: none;
        border: none;
        cursor: pointer;
        background: #121212;
        color: white;
      }
    </style>
  </head>
  <body>
    <div class="form-container">
      <form action="/Sprint-test/trajet/save" class="form">
        <h1 class="title">Inserer un trajet</h1>
        <input type="hidden" name="etat" value="1">
        <div class="input-container">
          <label for="depart">Depart</label>
          <input type="text" class="input" name="depart" id="depart">
        </div>
        <div class="input-container">
          <label for="arrivee">Arrivee</label>
          <input type="text" class="input" name="arrivee" id="arrivee">
        </div>
        <div class="input-container">
          <label for="date_depart">Date du depart</label>
          <input type="date" class="input" name="date_depart" id="date_depart">
        </div>
        <div class="input-container">
          <label for="heure_depart">Heure du depart</label>
          <input type="time" class="input" name="heure_depart" id="heure_depart">
        </div>
        <div class="input-container">
          <label for="chauffeur">Chauffeur</label>
          <div class="select-container">
            <select name="chauffeur" id="chauffeur">
              <%
              for (Chauffeur c : chauffeurs) {
                out.println("<option value='" + c.getId() + "'>" + c.getNom() + "</option>");
              }
              %>
            </select>
          </div>
        </div>
        <div class="input-container">
          <label for="voiture">Voiture</label>
          <div class="select-container">
            <select name="voiture" id="voiture">
              <%
              for (Voiture v : voitures) {
                out.println("<option value='" + v.getId() + "'>" + v.getModele() + "</option>");
              }
              %>
            </select>
          </div>
        </div>
        <!-- <input type="file" name="file" id=""> -->
        <input type="submit" value="Inserer" class="btn">
      </form>
    </div>
  </body>
</html>
