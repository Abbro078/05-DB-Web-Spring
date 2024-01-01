<!DOCTYPE html>
<html>
<head>
    <title>Grading System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            color: #353c4e;
            margin: 0;
        }

        .Main-container {
            height: 100vh;
            background-color: #f2f2f2;
        }

        .container-login {
            position: absolute;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #f2f2f2;
        }

        .wrap-login {
            max-width: 350px;
            margin: 0 auto;
        }

        .login-header {
            text-align: center;
            margin-bottom: 20px;
        }

        .login-header h2 {
            color: #353c4e;
        }

        .login-form {
            width: 100%;
            max-width: 350px;
            margin: 0 auto;
        }

        .login-form-title {
            display: block;
            text-align: center;
            font-size: 24px;
            margin-bottom: 20px;
        }

        .wrap-input {
            position: relative;
            margin-bottom: 20px;
        }

        .input {
            width: 100%;
            padding: 10px;
            border: 1px solid #d9d9d9;
            border-radius: 5px;
            outline: none;
        }

        .focus-input {
            position: absolute;
            width: 100%;
            height: 2px;
            background: #353c4e;
            bottom: 0;
            left: 0;
            pointer-events: none;
            transform: scaleX(0);
            transition: transform 0.4s;
        }

        .input:focus + .focus-input {
            transform: scaleX(1);
        }

        .symbol-input {
            position: absolute;
            font-size: 18px;
            color: #999999;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
        }

        .login-form-btn-container {
            text-align: center;
            margin-top: 20px;
        }

        .login-form-btn {
            padding: 10px 30px;
            background-color: #353c4e;
            color: #ffffff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .login-form-btn:hover {
            background-color: #2c3144;
        }

        p {
            margin: 0;
        }

        font[error] {
            color: red;
        }
    </style>
</head>
<body>
<div class="Main-container">
    <div class="container-login">
        <div class="wrap-login">
            <div class="login-header">
                <h2>Grading System</h2>
            </div>
            <form class="login-form" action="/login" method="POST">
                <span class="login-form-title">Login</span>

                <div class="wrap-input">
                    <input type="text" class="input" name="name" placeholder="Email" required>
                    <span class="focus-input"></span>
                    <span class="symbol-input">
                            <i class="fa fa-envelope" aria-hidden="true"></i>
                        </span>
                </div>
                <div class="wrap-input">
                    <input type="password" class="input" name="password" placeholder="Password" required>
                    <span class="focus-input"></span>
                    <span class="symbol-input">
                            <i class="fa fa-lock" aria-hidden="true"></i>
                        </span>
                </div>

                <div class="login-form-btn-container">
                    <button class="login-form-btn" type="submit">Login</button>
                </div>
                <div style="text-align: center;">
                    <p error>${errorMessage}</p>
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
