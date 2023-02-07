from PIL import Image
from pathlib import Path

path = Path("G:/Cloud Project/CLud Test/rat.jpeg")
# Open an image file
with Image.open(path) as im:
    # Convert the image to a byte array
    byte_array = bytearray(im.tobytes())
    # Print the byte array
    with open('G:/Cloud Project/CLud Test/readme.txt', 'wb') as f:
        f.write(byte_array)

