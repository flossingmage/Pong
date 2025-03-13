package Main.GameFolder.GameComp;
public class CollisionHandler {
    public Paddle paddle1;
    public Paddle paddle2;
    public Ball ball;

    public CollisionHandler(Paddle paddle1, Paddle paddle2, Ball ball) {
        this.paddle1 = paddle1;
        this.paddle2 = paddle2;
        this.ball = ball;
    }

    /**
     * Check if the ball collides with the paddles or the walls
     * and change the angle of the ball accordingly
     */
    public void checkCollision() {
        if (ball.x < paddle1.x + paddle1.width && ((ball.y > paddle1.y && ball.y < paddle1.y + paddle1.height)
                || (ball.y + ball.size > paddle1.y && ball.y + ball.size < paddle1.y + paddle1.height))) {
            ball.angle = -(paddle1.y + (paddle1.height / 2) - ball.y);
            if (ball.speed < 18)
                ball.speed += 0.5;
        }

        if (ball.x + ball.size > paddle2.x && ((ball.y > paddle2.y && ball.y < paddle2.y + paddle2.height)
                || (ball.y + ball.size > paddle2.y && ball.y + ball.size < paddle2.y + paddle2.height))) {
            ball.angle = (paddle2.y + (paddle2.height / 2) - ball.y) + 180;
            if (ball.speed < 18)
                ball.speed += 0.5;
        }

        if (ball.y < 0 || ball.y + ball.size > 600) {
            ball.angle *= -1;
        }
    }
}
