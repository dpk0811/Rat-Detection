import PIL.Image as Image
import io

with open("G:/Cloud Project/CLud Test/abc.txt", "rb+") as f:
    print(type(f.read()))
    image_data = bytearray(f.read())
data = Image.open(io.BytesIO(image_data))
data.save("G:/Cloud Project/CLud Test/lauda.jpg")
