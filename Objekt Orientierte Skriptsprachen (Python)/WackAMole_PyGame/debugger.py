class Debug:
    def __init__(self, mode):
        self.mode = mode

    def log(self, message):
        if self.mode is "debug":
            print("> DEBUG: " + str(message))