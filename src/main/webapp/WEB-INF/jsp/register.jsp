<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!DOCTYPE html>

<html lang="en">
    <head>
        <title>Your personal Twitter is two steps from you</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="/js/videobg.js"></script>
        <link href="/css/signin.css" rel="stylesheet">
        <link href="/css/videobg.css" rel="stylesheet">
    </head>
    <body>

        <div class="homepage-hero-module">
            <div class="video-container">
                <div class="filter">
                    <div class="container">

                      <sf:form method="POST" commandName="user" class="form-signin">
                        <h2 class="form-signin-heading">Register now</h2>
                        <sf:errors path="*" element="div" cssClass="alert alert-danger" />

                        <sf:label path="username" class="sr-only">Username</sf:label>
                        <sf:input path="username" id="username" class="form-control" placeholder="Username" required="true" autofocus="true" />

                        <sf:label path="email" class="sr-only">Email</sf:label>
                        <sf:input path="email" type="email" id="email" class="form-control" placeholder="Email" required="true" />

                        <sf:label path="password" class="sr-only">Password</sf:label>
                        <sf:input type="password" path="password" id="password" class="form-control" placeholder="Password" required="true" />

                        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
                        <center>Already have an account?
                        <br><a href="/login">Sign in here</a></center>
                    </sf:form>

                    </div> <!-- /container -->

                </div>
                <video autoplay loop class="fillWidth">
                    <source src="/video/bg.mp4" type="video/mp4" />Your browser does not support the video tag. I suggest you upgrade your browser.
                    <source src="/vide/bg.webm" type="video/webm" />Your browser does not support the video tag. I suggest you upgrade your browser.
                </video>
                <div class="poster hidden">
                    <img src="/img/bg.jpg" alt="">
                </div>
            </div>
        </div>
    </body>

</html>

<%/*
<!DOCTYPE html>

<html lang="en">

    <body>
        <sf:form method="POST" commandName="user">
            <sf:errors path="*" element="div" cssClass="errors" />

            <sf:label path="username" cssErrorClass="error">Username</sf:label>:
            <sf:input path="username" cssErrorClass="error" />

            <br/>

            <sf:label path="email" cssErrorClass="error">Email</sf:label>:
            <sf:input path="email" cssErrorClass="error" />

            <br/>

            <sf:label path="password" cssErrorClass="error">Password</sf:label>:
            <sf:input type="password" path="password" cssErrorClass="error" />

            <br/>

            <input type="submit" value="Register" />
        </sf:form>
        <p>Already have an account? <a href="/login">Sign in here</a></p>
    </body>

</html>
*/%>