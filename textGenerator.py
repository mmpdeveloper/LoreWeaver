import sys
sys.path.append('/transformers')
# Adding the local transformers directory to the sys path
# creative writing generator
from transformers import GPT2LMHeadModel, GPT2Tokenizer, pipeline


class TextGenerator:
    def __init__(self, model_dir="models/gpt2"):
        # Load pre-trained model tokenizer (vocabulary)
        self.tokenizer = GPT2Tokenizer.from_pretrained(model_dir)
        # Load pre-trained model (weights)
        self.model = GPT2LMHeadModel.from_pretrained(model_dir)
        # Create the pipeline
        self.generator = pipeline("text-generation", model=self.model, tokenizer=self.tokenizer)

    def generate_text(self, prompt):
        try:
            generated_text = self.generator(prompt, max_length=500, num_return_sequences=1)[0]["generated_text"]
            return generated_text
        except Exception as e:
            return str(e)


if __name__ == "__main__":
    # Read the input prompt from standard input
    prompt = input().strip()

    # Create an instance of the TextGenerator class
    text_gen = TextGenerator()

    # Generate text based on the input prompt
    result = text_gen.generate_text(prompt)
    print(result)
