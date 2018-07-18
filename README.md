<img src="https://user-images.githubusercontent.com/37747169/38216106-d74a953e-36da-11e8-9028-684ea87c70fa.png" width=200>
<br>

# PRemote-PC
Remote PC controll application made during EUhack - Free Hacking

### What is it?3
PRemote is an application that allows you to control your from mobile.

### How to run?

1. Download the mobile and desktop app
2. &emsp;Setup ![firebase](https://firebase.google.com/) & Download google-services.json <br>
 &emsp;Copy it and paste it to ./PRemote-PC/ Folder <br>
 &emsp;and to ./PRemote-PC/app/ Folder <br>
3. Run **PC** application via terminal using following code: 
```
 nohup python3 app.py &
```
4. Connect to both devices from **SAME** account
5. &emsp;Click the Microphone button on phone <br>
&emsp; Say command (ex: "Play music") <br>
&emsp; Enjoy :) <br>
        
### How does it work?
Firebase real-time database makes it easy to implement simple & effective apps like this.
<ul>
  <img src="https://user-images.githubusercontent.com/37747169/38215899-37c19e22-36da-11e8-9e23-4305cb8fada2.png" width=400> 
  <br>
  <li> Android sends command to Firebase Real-Time database. </li>
  <li> PC listens to database change (specifically users scope), it parses command and executes method. </li>
  <br>
  <img src="https://user-images.githubusercontent.com/37747169/38215717-c34ba646-36d9-11e8-972d-cb76cff849dc.png" width=400>
</ul>
