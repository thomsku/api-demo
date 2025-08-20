import {Router} from "express";
import createClient from "openapi-fetch";
import {paths} from './schema'

const router = Router();
const client = createClient<paths>({baseUrl: 'http://localhost:8080'})

router.get('/greeting-openapi-fetch', async (req, res) => {
    const { data, error} = await client.GET("/greeting", {params: {query: {name: "Thomas"}}})
    res.send(data.content)
})

export default router;