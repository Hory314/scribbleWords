# Scribble Custom Words

Simple web app that allows you and your friends you play with to add, store and get your own words for game [https://skribbl.io/](https://skribbl.io/).


Web app administrator can accept, reject or edit words that users added to database.


Users can easily copy accepted words from main site and paste them into game _custom words_ section.

To use application you have to:
* deploy this project on server (you can use OpenShift)
* connect to MySQL database using config below:
   * Host: `jws-app-mysql`
   * Database name: `root`
   * Database port: `3306`
   * Username: `userE7N`
   * Password: `GfGS3Axr`
* To access Admin Panel use go to `/adminpanel` and use:
   * login: `admin`
   * password: `skribbl-314`
   
***
#### Future releases

I'm planning allow users to create accounts as admins of their own word's data base.

They could share links to their data base with friends and accept words they enter.

This feature won't require to deploy application on your own server.
