class Painting:
    museum = "Louvre"

    def __init__(self, title, artist, year):
        self.title = title
        self.artist = artist
        self.year = year


painting_title = input()
painting_artist = input()
painting_year = input()
painting = Painting(painting_title, painting_artist, painting_year)
print(f'"{painting.title}" by {painting.artist} ({painting.year}) hangs in the {painting.museum}.')
