# CS-1632-DELIVERABLE-6


updated_project.zip contains the web-site updated version, including minor fixes and a style template, which it was not the focus of the assignment


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
>    5.  create new database
>    6.  name it "project" (exactly)
>    7. import car.sql (in CS-1632-DELIVERABLE-6 directory)
>    8. uncheck "Partial Import"
>    9.  select Priveledges tab
>    10.  add user account (under "NEW")
>        username: client
>        password: client
>        hostname localhost (from dropdown menu)
>        Global priveledges: SELECT
>    11.  Hit "Go" once


6)  Test the webapp by playing around (email pcn7@pitt.edu or maa261@pitt.edu for more help)
7)  keep XAMPP running for the tests


JAVA dependencies:
1)  JUnit v.4.12.0 and org.hamcrest-core v.1.3.0
2)  selenium-java-2.52.0.jar (must be this exact version because newer version does not have htmlunit webdriver)
3)  selenium-server-standalone-2.52.0.jar (must be this exact version because newer version does not have htmlunit webdriver)
