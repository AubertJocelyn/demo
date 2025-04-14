import os 
from PIL import Image
import os.path

def verifsiimage(fic):
    try:
        image= Image.open(fic)
        return True
    except :
        return False

def VerifQueDesIMG(directory):
    if os.path.isdir(directory):
        for fichier in os.listdir(directory) :
            chemin = os.path.join(directory, fichier)
            if not verifsiimage(chemin):
                return False
    return True 


