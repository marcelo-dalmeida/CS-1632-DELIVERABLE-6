# CS-1632-DELIVERABLE-6

Marcelo d'Almeida and Philip Ni

IMPORTANT:
1)  Webapp uses XAMPP/LAMPP stack
2)  Run Apache web server and MySQL
3)  place CS-1632-DELIVERABLE-6 directory into htdocs in xampp folder
4)  project url is "http://localhost/CS-1632-DELIVERABLE-6/project"
5)  Use phpMyAdmin (from http://localhost) to create databases:
>    1.  create new database
>    2.  name it "login" (exactly)
>    3.  import login.sql (in CS-1632-DELIVERABLE-6 directory)
>    4.  uncheck "Partial Import"
>    5.  select Priveledges tab
>    6.  add user account (under "NEW")
>        username: client
>        password: client
>        hostname localhost (from dropdown menu)
>        Global priveledges: SELECT
>    7.  Hit "Go" once
>    8.  create new database
>    9.  name it "project" (exactly)
>    10. import car.sql (in CS-1632-DELIVERABLE-6 directory)
>    11. uncheck "Partial Import"

6)  Test the webapp by playing around (email pcn7@pitt.edu or maa261@pitt.edu for more help)
7)  keep XAMPP running for the tests


JAVA dependencies:
1)  JUnit v.4.12.0 and org.hamcrest-core v.1.3.0
2)  selenium-java-2.52.0.jar (must be this exact version because newer version does not have htmlunit webdriver)
3)  selenium-server-standalone-2.52.0.jar (must be this exact version because newer version does not have htmlunit webdriver)
