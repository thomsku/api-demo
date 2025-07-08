import { Router } from "express";

interface Greeting {
    id: number;
    content: string;
}

const router = Router();

router.get('/greeting', async (req, res) => {
    const response = await fetch(`http://localhost:8080/greeting?name=${req.query.name ?? ''}`);
    const greeting: Greeting = await response.json();
    res.send(greeting.content);
});

export default router;