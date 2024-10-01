const { VITE_GPT_API_KEY } = import.meta.env;
import OpenAI from "openai";
const openai = new OpenAI({
  apiKey: VITE_GPT_API_KEY,
  dangerouslyAllowBrowser: true,
});
async function chatgptApi(keyword) {
  
  const stream = await openai.chat.completions.create({
    model: "gpt-3.5-turbo",
    messages: [{ role: "system", content: `30글자 내외로 ${keyword}에 대해서 설명해줘` }],
  })
  
  return stream.choices[0].message.content


}


export { chatgptApi };
