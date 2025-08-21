import {Router} from "express";
import createClient from "openapi-fetch";
import {paths} from '../api/schema'

const router = Router();
const client = createClient<paths>({baseUrl: 'http://localhost:8080'})

router.get('/greeting-openapi-fetch', async (req, res) => {
    const { data, error} = await client.GET("/greeting", {params: {query: {name: req.query.name as string}}})
    if (error) {
        res.status(error.status).send(error.message)
    }
    res.send(data.content)
})

export default router;