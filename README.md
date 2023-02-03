# Wiremock Stateful Behavior With Kotlin And Jetpack Compose
Small example that helps to manage states from backend requests.

# 👨‍💻 To run this project you should follow the next steps

Step 1: Requirements (Skip for this example)
------------------------------

1. Make sure you have linked Wiremock as a dependency: https://wiremock.org/#open-source-get-started
2. If you have any problem, you can download it from: https://repo1.maven.org/maven2/com/github/tomakehurst/wiremock-standalone/
3. Create your JSON files simulating API server.
4. Run server with `bash java –jar <downloaded-file-name>.jar`.


Step 2: Clone Repository
-------------------------------    

This command allows you to clone this repository

```bash
git clone https://github.com/nekomaruh/wiremock_stateful_behavior_kotlin_jetpack_compose.git
```


Step 3: Build & Run Project
-------------------------------

Run Wiremock server with this command

```bash
sh wiremock/start-wiremock.sh
```

Run app with Android Studio
    
    
Explanation
-------------------------------

The app is composed by 2 screens, Main Screen and Stage Screen:

<img src="https://user-images.githubusercontent.com/42304227/216477991-dd2205f1-c2b9-4f6f-95e1-b056f8654003.png" width="300">

A UUID is auto-generated in Main Screen and then passed by arguments to Stage Screen to simulate a payment code.

Stage Screen contains different states which the payment can go trough.
STATE 1: Started
STATE 2: Init
STATE 3: Created
STATE 4: Validated
STATE 5: Authorized

When user clicks `START PULL` button wiremocks will start to work as a state machine, getting the next possible states. Once finished last possible requested state attempt, pulling will stop as shown. You can see server requests in Logs as well.

<p align="left">
      <img src="https://user-images.githubusercontent.com/42304227/216478724-b6e249a1-8ae3-4df2-9b4b-14264019cb64.png" width="300">
      <img src="https://user-images.githubusercontent.com/42304227/216478835-ab887a34-baef-462b-a15c-13747ae83724.png" width="300">
</p>

You can stop pulling data with `RESET SCENARIO` button at any time you want.

🥳 Congratulations!!, now you will have a local environment running with Wiremock!
