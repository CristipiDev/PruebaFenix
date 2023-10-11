# PruebaFenix
<p>

  <p>

Calendar App that enables to add a new lesson and tag students in it. Built in Jetpack Compose, using Dagger Hilt for dependencies and Room for DB. I also used MVVM and clean architecture for the layout of the project. 

<a href="https://img.shields.io/github/stars/CristipiDev/PruebaFenix?style=social">
  <img style="margin-right: 4px; margin-bottom: 8px" alt="Give this library a star" src="https://img.shields.io/github/stars/CristipiDev/PruebaFenix?style=social">
</a>

<a href="https://github.com/CristipiDev/">
  <img style="margin-right: 4px; margin-bottom: 8px" alt="Follow me on GitHub" src="https://img.shields.io/github/followers/CristipiDev?style=social&label=Follow">
</a>

# Main page - Calendar

This is the main page of the app, as you can see it's a calendar arrange by day. Each day can have diferent types of lessons, those lessons are organized by the start time and are dicided into 2 bloks: "Morning" and "Afternoon". If you click in the arrow buttons above the calendar, you can change the day ( < -1 day, > +1 day), and ultimately if you want to go farther than 1 day you can click in the center (the date) and a popup is going to open.

<table>
  <tr>
    <td><img src="https://i.imgur.com/B4LlRq9.png" title="source: imgur.com" width="300"/></td>
    <td><img src="https://i.imgur.com/XYsnomj.png" title="source: imgur.com" width="300"/></td>
  </tr>
</table>

# New Lesson page
To navegate to this page you will need to click in the "+" button at the top of the Main page. </br>
Once you access to this page, you will be able to create a new Lesson with the following pareameters: Day of the week (as seen in the picture a dropdown will show to select the day), color of the lesson (the color for the lesson's background), name of the lesson, start time, end time and number of vacancies. If you click in the "x" at the top of the page you will loose all the data that you didn't save. To save the lesson click on "Guardar". </br>

Worth mencion is that: all the data are required in order to save the lesson, if something isn't filled or is filled incorrectly, a Toast is going to show with the info os the error (as shown on the last image).

<table>
  <tr>
    <td><img src="https://i.imgur.com/Hw10fMY.png" title="source: imgur.com" width="300"/></td>
    <td><img src="https://i.imgur.com/z8erwVp.png" title="source: imgur.com" width="300"/></td>
    <td><img src="https://i.imgur.com/m3H9LmV.png" title="source: imgur.com" width="300"/></td>
  </tr>
</table>
