# -Z auto-zoom

largeur=$1
hauteur=$2
x=$3
y=$4

IFS='§' read -r -a preCheminsImages <<< "$5"

cheminsImages=()

for preChemin in "${preCheminsImages[@]}"; do
  cheminsImages+=("$preChemin")
done

feh -Z --geometry ${largeur}x${hauteur}+${x}+${y} "${cheminsImages[@]}"










