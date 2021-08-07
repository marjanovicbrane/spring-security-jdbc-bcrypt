# SPRING SECURITY with  JDBC AUTHENTICATION and AUTHORIZATION using custom login page and BCRYPT  algorithm

In this project I used Spring Security for JDBC authentication and authorization with custom login page.

![1](https://user-images.githubusercontent.com/61464267/128599859-579db26a-7e12-4603-a6e2-a52112da9091.PNG)

User can have 3 roles:employee, manager and admin and he also have appropriate endpoint for every role.With appropriate role, only that user can access some additional information and pages on the web app.For example user “john” have only role EMPLOYEE, and he can only access this page.

![2](https://user-images.githubusercontent.com/61464267/128599906-2a32335c-66ed-49ab-babc-769419851fae.PNG)

Some users can have more roles, for example user “mary” have two roles EMPLOYEE and MANAGER and only user with role MANAGER can access this additional information and page Leadership Meeting on the web app.

![image](https://user-images.githubusercontent.com/61464267/128599930-f0e115c8-8f1a-457b-855c-41e99e9e3178.png)

![4](https://user-images.githubusercontent.com/61464267/128599956-c1955653-2b85-4550-8097-90c123c44ec2.PNG)

User susan also have 2 roles EMPLOYEE and ADMIN and only user with role ADMIN can access this additional information and page IT Systems Meeting on the web app.

![image](https://user-images.githubusercontent.com/61464267/128600009-6dbce57f-a1fa-4c53-8386-65d5279613ce.png)

![6](https://user-images.githubusercontent.com/61464267/128600035-26d42852-9153-4bcc-a579-0df85fee97fc.PNG)

If some other user which is not authorized trys to access some additional information and pages, he will get access denied page with message.

![image](https://user-images.githubusercontent.com/61464267/128600108-5f30c27f-4e83-4bc4-820c-b168bef13b17.png)

In this project I used bcrypt algorithm for password encryption, this is one-way encrypted hashing, so the password in the database can never be decrypted.

To protect against CSRF attacks I used additional authentication data/token into all HTML forms.On this way we can prevent evil website to tricks us into executing an action on a web application that you are currently logged in.For each request we have randomly generated token and Spring Security verifies token before processing.I also added logout button, because we want to logout the user from the system, on that way we also removing http session, cookies, etc…
