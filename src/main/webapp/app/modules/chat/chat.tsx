import axios from 'axios';
import { getPresudaPoruka, getPresudaSema } from './chat_poruke/getPresudaPoruka';

const url = 'https://api.openai.com/v1/chat/completions';
const key = '';
const userKey = '';

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
