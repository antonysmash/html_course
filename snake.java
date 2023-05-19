<!DOCTYPE html>
<html>
<head>
    <title>Snake Game</title>
</head>
<body>
    <canvas id="gameCanvas" width="640" height="480"></canvas>

    <script>
        // Set up the canvas and game variables
        var canvas = document.getElementById("gameCanvas");
        var ctx = canvas.getContext("2d");
        var snake = [{x: 10, y: 10}];
        var food = {x: 0, y: 0};
        var direction = "right";
        var score = 0;

        // Generate initial food location
        generateFood();

        // Main game loop
        setInterval(gameLoop, 100);

        function gameLoop() {
            // Clear the canvas
            ctx.clearRect(0, 0, canvas.width, canvas.height);

            // Move the snake
            var head = {x: snake[0].x, y: snake[0].y};
            if (direction === "right") head.x++;
            if (direction === "left") head.x--;
            if (direction === "up") head.y--;
            if (direction === "down") head.y++;
            snake.unshift(head);
            snake.pop();

            // Check for collision with food
            if (head.x === food.x && head.y === food.y) {
                generateFood();
                score++;
            }

            // Check for collision with walls or self
            if (head.x < 0 || head.x >= canvas.width/10 || head.y < 0 || head.y >= canvas.height/10 || isCollision()) {
                alert("Game Over");
                location.reload();
            }

            // Draw the game elements
            drawSnake();
            drawFood();
            drawScore();
        }

        function drawSnake() {
            ctx.fillStyle = "green";
            for (var i = 0; i < snake.length; i++) {
                ctx.fillRect(snake[i].x*10, snake[i].y*10, 10, 10);
            }
        }

        function drawFood() {
            ctx.fillStyle = "red";
            ctx.fillRect(food.x*10, food.y*10, 10, 10);
        }

        function drawScore() {
            ctx.fillStyle = "black";
            ctx.font = "20px Arial";
            ctx.fillText("Score: " + score, 10, 30);
        }

        function generateFood() {
            food.x = Math.floor(Math.random() * (canvas.width/10));
            food.y = Math.floor(Math.random() * (canvas.height/10));
        }

        function isCollision() {
            for (var i = 1; i < snake.length; i++) {
                if (snake[0].x === snake[i].x && snake[0].y === snake[i].y) {
                    return true;
                }
            }
            return false;
