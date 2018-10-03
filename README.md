<p align="center"><img src="https://user-images.githubusercontent.com/37747169/46416590-ee9a8480-c738-11e8-8de2-ef8957e46596.png" align="center" width=50%></p>


# PRemote-Android
Remote PC controll application made during EUhack - Free Hacking. <br/>
This repository is **Android side** of PRemote.

### What is it?3
PRemote is an application that allows you to control your PC from mobile.

### How to run?

1. Download the mobile and desktop app
```
git clone https://github.com/PRemoteApp/Premote-PC.git
git clone https://github.com/PRemoteApp/Premote-Android.git
```
2. &emsp;Create project on ![firebase](https://firebase.google.com/) & Download google-services.json <br>
 &emsp;Copy it and paste it to ./PRemote-PC/ Folder <br>
 &emsp;and to ./PRemote-Android/app/ Folder <br>
3. Run **PC** application via terminal using following code: 
```
 # Ubuntu:
 nohup python3 app.pyw &
 
 # Windows:
 python3 app.pyw
```
4. Connect to both devices from **SAME** account
5. &emsp; Click the Microphone button on phone <br>
&emsp; Say command (ex: "Play music") <br>
&emsp; Enjoy :) <br>
        
### How does it work?
Firebase real-time database makes it easy to implement simple & effective apps like this.
<ul>
  <img src="https://user-images.githubusercontent.com/37747169/38215899-37c19e22-36da-11e8-9e23-4305cb8fada2.png" width=400> 
  <br>
  <li> Android sends command to Firebase Real-Time database. </li>
  <li> PC listens to database change (specifically users scope), parses command and executes method. </li>
  <br>
  <img src="https://user-images.githubusercontent.com/37747169/38215717-c34ba646-36d9-11e8-972d-cb76cff849dc.png" width=400>
</ul>
