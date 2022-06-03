#Scene Void Framework
Project was created using the scene void framework, the base idea of this framework is to focus more on the logic of the web application and less on connection methods and other libraries.

How to setup the SC Version of the website

1. Copy these files to the main directory where the web application will be hosted

2. Navigate to .system/.framework/.config and change the database details to your own database details, note there is a test section and a live section in the file, if the config file is marked as test only test settings will be used while if marked live then live settings will be used

3. That is all

_Notes_

> The main web logic is found in .system/.site/

> There will be instances of a ".php" file in some folders, these files serve as a first execution script, in other words these scripts are executed before the main/requested script is executed. This allowed me to enclose certain parts of the system or provide quick logic to those parts.
