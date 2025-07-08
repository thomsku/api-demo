import { Router } from "express";
import {Greeting} from "../api/api-demo";

const router = Router();

router.get('/greeting-with-types', async (req, res) => {
    const response = await fetch(`http://localhost:8080/greeting?name=${req.query.name ?? ''}`);
    const greeting: Greeting = await response.json();
    res.send(greeting.content);
});

export default router;