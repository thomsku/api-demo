import { Router } from "express";
import * as API from "../build/index";
import { Configuration } from '../build/index';
const router = Router();

const greetingWs = () => {
    const configuration = new Configuration({ basePath: 'http://localhost:8080' });
    return new API.GreetingApi(configuration);
};

router.get('/greeting-openapi', async (req, res) => {
    const response = await greetingWs().getGreeting({name: req.query.name as string});
    res.send(response.content);
});

export default router;