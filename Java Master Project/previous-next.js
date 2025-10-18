//game features animation for next and previous selection
document.addEventListener('DOMContentLoaded', function() {
    let currentIndex = 0;
    const slides = document.querySelectorAll('.slide');
    
    const features = [
        {
            title: "Streak System",
            description: "This is where the consistency tracking in Java Programming helps you maintain your learning momentum every day."
        },
        {
            title: "Campaign Mode",
            description: "Track your journey through structured challenges. See your progress, unlock new stages, and conquer Java one level at a time."
        },
        {
            title: "Quiz",
            description: "Test your skills, earn rewards, and reinforce what you've learned—all through fast-paced, interactive quizzes."
        },
        {
            title: "Shop",
            description: "Spend your earned currencies on health points, and hint. Learning pays off—literally"
        },
        {
            title: "Decks",
            description: "Review key concepts with handout decks. Each one opens a guide to prepare you before diving into the quiz."
        },
        {
            title: "Game Account",
            description: "Your coding identity in the Java Master universe. See your rank, best streak, and join date. As well as your display name, STI College branch, and year level—all in one profile."
        }
    ];

    function updateSlides() {
        slides.forEach((slide, index) => {
            slide.classList.remove('prev', 'active', 'next', 'hidden');
            
            if (index === currentIndex) {
                slide.classList.add('active');
            } else if (index === (currentIndex - 1 + slides.length) % slides.length) {
                slide.classList.add('prev');
            } else if (index === (currentIndex + 1) % slides.length) {
                slide.classList.add('next');
            } else {
                slide.classList.add('hidden');
            }
        });

        // Update description
        document.getElementById('feature-title').textContent = features[currentIndex].title;
        document.getElementById('feature-text').textContent = features[currentIndex].description;
    }

    // Make functions global so onclick can access them
    window.nextSlide = function() {
        currentIndex = (currentIndex + 1) % slides.length;
        updateSlides();
    }

    window.prevSlide = function() {
        currentIndex = (currentIndex - 1 + slides.length) % slides.length;
        updateSlides();
    }

    // Initialize
    updateSlides();
});