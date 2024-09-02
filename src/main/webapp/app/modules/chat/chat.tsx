import axios from 'axios';
import { getPresudaPoruka, getPresudaSema } from './chat_poruke/getPresudaPoruka';
import { createPresudaPoruka, createPresudaPrimer1, createPresudaPrimer2 } from './chat_poruke/createPresudaPoruka';

const url = 'https://api.openai.com/v1/chat/completions';
const key = '';
const userKey = '';

const MAX_MESSAGE_LENGTH = 10000;

export const chatExtractPresuda = async (text: string) => {
  try {
    const apiUrl = url;
    const apiKey = userKey;
    const headers = {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${key}`,
      },
      timeout: 180000,
    };

    if (text.length > MAX_MESSAGE_LENGTH) {
      text.substring(0, MAX_MESSAGE_LENGTH);
    }

    const requestBody = {
      messages: [
        { role: 'user', content: getPresudaPoruka },
        { role: 'user', content: text },
      ],
      model: 'gpt-4o-mini',
      functions: [getPresudaSema],
      function_call: { name: 'get_presuda_info' },
    };

    const { data } = await axios.post(apiUrl, requestBody, headers);

    console.log(data);

    return data.choices[0].message.function_call.arguments;
  } catch (error) {
    console.error('Error sending message:', error);
  }
};

export const chatCreatePresuda = async (presuda: string) => {
  try {
    const apiUrl = url;
    const apiKey = userKey;
    const headers = {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${key}`,
      },
      timeout: 180000,
    };

    const requestBody = {
      messages: [
        { role: 'user', content: createPresudaPoruka },
        { role: 'user', content: createPresudaPrimer1 },
        { role: 'user', content: createPresudaPrimer2 },
        { role: 'user', content: JSON.stringify(presuda) },
      ],
      model: 'gpt-4o-mini',
    };

    const { data } = await axios.post(apiUrl, requestBody, headers);

    console.log(data);

    return data.choices[0].message.content;
  } catch (error) {
    console.error('Error sending message:', error);
  }
};
