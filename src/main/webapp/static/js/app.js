// 全局JavaScript功能

// 实时事件功能
class RealTimeEvents {
    constructor(matchId) {
        this.matchId = matchId;
        this.lastEventId = '0';
        this.isActive = false;
    }

    start() {
        this.isActive = true;
        this.pollEvents();
    }

    stop() {
        this.isActive = false;
    }

    async pollEvents() {
        if (!this.isActive) return;

        try {
            const response = await fetch(`/matches/events/realtime/${this.matchId}?lastEventId=${this.lastEventId}`);
            const events = await response.json();

            if (events.length > 0) {
                this.displayEvents(events);
                this.lastEventId = events[events.length - 1].eventId;
            }
        } catch (error) {
            console.error('实时事件加载失败:', error);
        }

        // 5秒后再次轮询
        setTimeout(() => this.pollEvents(), 5000);
    }

    displayEvents(events) {
        const container = document.getElementById('realTimeEventsContent');
        if (!container) return;

        events.forEach(event => {
            const eventElement = this.createEventElement(event);
            container.insertBefore(eventElement, container.firstChild);

            // 添加高亮效果
            eventElement.classList.add('highlight');
            setTimeout(() => eventElement.classList.remove('highlight'), 2000);
        });

        // 显示实时事件区域
        document.getElementById('realTimeEvents').style.display = 'block';
    }

    createEventElement(event) {
        const div = document.createElement('div');
        div.className = 'alert alert-info mb-2';
        div.innerHTML = `
            <strong>${event.formattedTime}</strong> - ${event.eventDescription}
            <small class="text-muted float-end">${new Date().toLocaleTimeString()}</small>
        `;
        return div;
    }
}

// 页面加载完成后初始化
document.addEventListener('DOMContentLoaded', function() {
    // 如果有比赛ID，初始化实时事件
    const matchIdElement = document.querySelector('[data-match-id]');
    if (matchIdElement) {
        const matchId = matchIdElement.dataset.matchId;
        const realTimeEvents = new RealTimeEvents(matchId);
        realTimeEvents.start();
    }

    // 自动隐藏警告消息
    setTimeout(() => {
        const alerts = document.querySelectorAll('.alert:not(.alert-permanent)');
        alerts.forEach(alert => {
            alert.style.transition = 'opacity 0.5s';
            alert.style.opacity = '0';
            setTimeout(() => alert.remove(), 500);
        });
    }, 5000);
});

// 工具函数
const EsportsUtils = {
    // 格式化时间
    formatTime: function(seconds) {
        const mins = Math.floor(seconds / 60);
        const secs = seconds % 60;
        return `${mins.toString().padStart(2, '0')}:${secs.toString().padStart(2, '0')}`;
    },

    // 防抖函数
    debounce: function(func, wait) {
        let timeout;
        return function executedFunction(...args) {
            const later = () => {
                clearTimeout(timeout);
                func(...args);
            };
            clearTimeout(timeout);
            timeout = setTimeout(later, wait);
        };
    }
};