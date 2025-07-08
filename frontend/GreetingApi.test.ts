import assert from 'node:assert/strict';
import { beforeEach, describe, it } from 'node:test';
import { MockAgent, type MockPool, setGlobalDispatcher } from 'undici';
import * as API from "./build/index";
import { Configuration, Greeting } from './build/index';

const greetingWs = () => {
    const configuration = new Configuration({ basePath: 'http://localhost:8080' });
    return new API.GreetingApi(configuration);
};

describe('greetingWs', { concurrency: true }, () => {
    let client: MockPool;
    beforeEach(() => {
        const agent = new MockAgent();
        setGlobalDispatcher(agent);
        client = agent.get('http://localhost:8080');
    });

    it('kaller /greeting uten query parameter dersom name ikke er satt', async () => {
        const endpoint = '/greeting';
        const code = 200;
        const data: Greeting = {
            id: 1,
            content: 'Hello, World!'

        }

        client
            .intercept({
                path: endpoint,
                method: 'GET',
            })
            .reply(code, data);

        assert.deepEqual(await greetingWs().getGreeting(), data);
    });
    it('kaller /greeting med query parameter name', async () => {
        const endpoint = '/greeting?name=Test';
        const code = 200;
        const data: Greeting = {
            id: 1,
            content: 'Hello, Test!'

        }

        client
            .intercept({
                path: endpoint,
                method: 'GET',
            })
            .reply(code, data);

        assert.deepEqual(await greetingWs().getGreeting({name: "Test"}), data);
    });
});
