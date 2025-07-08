import express from "express";
import greetingRouter from "./routes/greeting";
import greetingWithTypesRouter from "./routes/greetingWithTypes";
import greetingOpenapi from "./routes/greetingOpenapi";

const app = express()
const port = 3000

app.use(greetingOpenapi);
app.use(greetingRouter);
app.use(greetingWithTypesRouter);

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
})
